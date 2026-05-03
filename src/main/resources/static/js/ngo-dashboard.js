const BASE_URL = "/api";

function goProfile() {
    window.location.href = "ngo-profile.html";
}

// ✅ NGO WELCOME
function setWelcome() {
    const user = JSON.parse(localStorage.getItem("user"));
    if (user && user.name) {
        document.getElementById("welcomeText").innerText =
            `Hello ${user.name} Organisation, Welcome Back 👋`;
    }
}

async function getNgoId() {
    const user = JSON.parse(localStorage.getItem("user"));
    const res = await fetch(`${BASE_URL}/ngos/email?email=${user.email}`);
    const ngo = await res.json();
    return ngo.id;
}

async function loadPending() {
    const ngoId = await getNgoId();
    const res = await fetch(`${BASE_URL}/donations/ngo/pending/${ngoId}`);
    const data = await res.json();
    render(data, true);
}

async function loadHistory() {
    const ngoId = await getNgoId();
    const res = await fetch(`${BASE_URL}/donations/ngo/history/${ngoId}`);
    const data = await res.json();
    render(data, false);
}

function getStatusClass(status) {
    if (status === "APPROVED") return "status approved";
    if (status === "REJECTED") return "status rejected";
    return "status pending";
}

function render(data, isPending) {

    const container = document.getElementById("donations");
    container.innerHTML = "";

    if (!data || data.length === 0) {
        container.innerHTML = "<p>No data</p>";
        return;
    }

    data.forEach(d => {

        let formattedDate = d.pickupDateTime
            ? new Date(d.pickupDateTime).toLocaleString()
            : "N/A";

        container.innerHTML += `
            <div class="card">

                <h3>👤 ${d.user?.name || "User"}</h3>

                <p><b>📦 Type:</b> ${d.donationType}</p>

                ${d.donationType === "FOOD" ? `
                    <p><b>🍲 Food Type:</b> ${d.foodType || "N/A"}</p>
                    <p><b>👥 Quantity:</b> ${d.quantity || "N/A"} persons</p>
                ` : `
                    <p><b>💰 Amount:</b> ₹${d.amount || 0}</p>
                `}

                <p><b>Status:</b> 
                    <span class="${getStatusClass(d.status)}">${d.status}</span>
                </p>

                <p><b>🚚 Delivery:</b> ${d.deliveryType}</p>
                <p><b>📅 Date:</b> ${formattedDate}</p>

                <p><b>📞 Phone:</b> ${d.contactPhone}</p>
                <p><b>📍 Address:</b> ${d.pickupAddress || "Self Delivery"}</p>

                ${isPending ? `
                    <input id="msg${d.id}" placeholder="Enter message">
                    <button onclick="update(${d.id}, 'APPROVED')">✅ Accept</button>
                    <button onclick="update(${d.id}, 'REJECTED')">❌ Reject</button>
                ` : `
                    <p><b>💬 Response:</b> ${d.responseMessage || "No message"}</p>
                `}

            </div>
        `;
    });
}

async function update(id, status) {
    const msgInput = document.getElementById("msg" + id);
    const msg = msgInput ? msgInput.value : "";

    await fetch(
        `${BASE_URL}/donations/${id}?status=${status}&message=${msg}`,
        { method: "PUT" }
    );

    alert("Updated successfully ✅");
    loadPending();
}

window.onload = () => {
    setWelcome(); // ✅ ADD THIS
    loadPending();
};