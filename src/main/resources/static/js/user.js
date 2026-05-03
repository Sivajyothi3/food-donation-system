const BASE_URL = "/api";

let allNGOs = [];

// LOAD NGOs
async function loadNGOs() {
    const res = await fetch(`${BASE_URL}/ngos/approved`);
    const ngos = await res.json();

    allNGOs = ngos;
    displayNGOs(ngos);
}

// DISPLAY (CARD STYLE)
function displayNGOs(list) {

    const container = document.getElementById("ngoList");
    if (!container) return;

    container.innerHTML = "";

    if (list.length === 0) {
        container.innerHTML = "<p>No NGOs found</p>";
        return;
    }

    list.forEach(ngo => {

        container.innerHTML += `
            <div class="card">
                <h3>${ngo.name}</h3>
                <button onclick="viewNGO(${ngo.id})">View Details</button>
            </div>
        `;
    });
}
// ✅ SET WELCOME MESSAGE
function setWelcome() {
    const user = JSON.parse(localStorage.getItem("user"));

    if (user && user.name) {
        document.getElementById("welcomeText").innerText =
            `Hello ${user.name}, Welcome Back 👋`;
    }
}

// SEARCH
function searchNGO() {
    const text = document.getElementById("search").value.toLowerCase();

    const filtered = allNGOs.filter(ngo =>
        ngo.name.toLowerCase().includes(text)
    );

    displayNGOs(filtered);
}

// VIEW NGO
function viewNGO(id) {
    localStorage.setItem("ngoId", id);
    window.location.href = "ngo-details.html";
}

// LOAD DETAILS PAGE
async function loadNGODetails() {

    const ngoId = localStorage.getItem("ngoId");

    if (!ngoId) return;

    const res = await fetch(`${BASE_URL}/ngos/approved`);
    const ngos = await res.json();

    const ngo = ngos.find(n => n.id == ngoId);

    if (!ngo) {
        document.getElementById("details").innerHTML = "<p>NGO not found</p>";
        return;
    }

    document.getElementById("details").innerHTML = `
        <h3>${ngo.name}</h3>

        <p><b>Email:</b> ${ngo.email || "N/A"}</p>
        <p><b>Phone:</b> ${ngo.phone || "N/A"}</p>
        <p><b>Address:</b> ${ngo.address || "N/A"}</p>
        <p><b>State:</b> ${ngo.state || "N/A"}</p>
        <p><b>Description:</b> ${ngo.description || "N/A"}</p>
        <p><b>Contact Person:</b> ${ngo.contactPersonName || "N/A"}</p>
    `;
}

// GO DONATE
function goDonate() {
    window.location.href = "donate.html";
}

// AUTO LOAD
window.onload = () => {

    setWelcome(); // ✅ ADD THIS

    if (document.getElementById("ngoList")) {
        loadNGOs();
        document.getElementById("search").addEventListener("input", searchNGO);
    }

    if (document.getElementById("details")) {
        loadNGODetails();
    }
};