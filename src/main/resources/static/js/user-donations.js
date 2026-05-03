const BASE_URL = "/api";

function getStatusClass(status) {
    if (status === "APPROVED") return "status approved";
    if (status === "REJECTED") return "status rejected";
    return "status pending";
}

function loadMyDonations() {

    const user = JSON.parse(localStorage.getItem("user"));

    fetch(`${BASE_URL}/donations/user/${user.id}`)
        .then(res => res.json())
        .then(data => {

            const container = document.getElementById("donationList");
            container.innerHTML = "";

            data.forEach(d => {

                let formattedDate = d.pickupDateTime
                    ? new Date(d.pickupDateTime).toLocaleString()
                    : "N/A";

                container.innerHTML += `
                    <div class="card">

                        <h3>🏢 ${d.ngo.name}</h3>

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

                        <p><b>👤 Contact:</b> ${d.contactName} (${d.contactPhone})</p>
                        <p><b>📍 Pickup:</b> ${d.pickupAddress || "N/A"}</p>

                        <p><b>💬 NGO Response:</b> ${
                            d.responseMessage
                                ? d.responseMessage
                                : d.status === "PENDING"
                                    ? "Awaiting NGO review"
                                    : d.status === "APPROVED"
                                        ? "Accepted by NGO"
                                        : "Rejected by NGO"
                        }</p>

                    </div>
                `;
            });

        });
}

loadMyDonations();