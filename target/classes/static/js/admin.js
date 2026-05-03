const BASE_URL = "/api";

let currentView = "pending";

// ✅ LOAD PENDING
async function loadPending() {
    try {
        currentView = "pending";

        const res = await fetch(`${BASE_URL}/admin/ngos/pending`);
        const data = await res.json();

        render(data, true);
    } catch (err) {
        console.error(err);
        alert("Failed to load pending NGOs");
    }
}

// ✅ LOAD APPROVED
async function loadApproved() {
    try {
        currentView = "approved";

        const res = await fetch(`${BASE_URL}/admin/ngos/approved`);
        const data = await res.json();

        render(data, false);
    } catch (err) {
        console.error(err);
        alert("Failed to load approved NGOs");
    }
}

// ✅ LOAD REJECTED
async function loadRejected() {
    try {
        currentView = "rejected";

        const res = await fetch(`${BASE_URL}/admin/ngos/rejected`);
        const data = await res.json();

        render(data, false);
    } catch (err) {
        console.error(err);
        alert("Failed to load rejected NGOs");
    }
}

// ✅ RENDER FUNCTION (FULL SAFE VERSION)
function render(data, isPending) {

    const container = document.getElementById("data");
    container.innerHTML = "";

    if (!data || data.length === 0) {
        container.innerHTML = "<p>No NGOs found</p>";
        return;
    }

    data.forEach(ngo => {

        const govtFile = ngo.govtCertificatePath
            ? `<a href="/uploads/${ngo.govtCertificatePath}" target="_blank">View</a>`
            : "Not uploaded";

        const panFile = ngo.panCardPath
            ? `<a href="/uploads/${ngo.panCardPath}" target="_blank">View</a>`
            : "Not uploaded";

        container.innerHTML += `
            <div style="border:1px solid #ccc; padding:10px; margin:10px;">
                <h3>${ngo.name || "N/A"}</h3>

                <p><b>Email:</b> ${ngo.email || "N/A"}</p>
                <p><b>Phone:</b> ${ngo.phone || "N/A"}</p>
                <p><b>Address:</b> ${ngo.address || "N/A"}</p>
                <p><b>State:</b> ${ngo.state || "N/A"}</p>
                <p><b>Reg No:</b> ${ngo.registrationNumber || "N/A"}</p>
                <p><b>Description:</b> ${ngo.description || "N/A"}</p>
                <p><b>Contact Person:</b> ${ngo.contactPersonName || "N/A"}</p>
                <p><b>Status:</b> ${ngo.status || "N/A"}</p>

                <p><b>Govt Certificate:</b> ${govtFile}</p>
                <p><b>PAN Card:</b> ${panFile}</p>

                ${isPending ? `
                    <button onclick="approve(${ngo.id})">Approve</button>
                    <button onclick="reject(${ngo.id})">Reject</button>
                ` : ""}

                <button onclick="deleteNGO(${ngo.id})">Delete</button>
            </div>
        `;
    });
}

// ✅ APPROVE
async function approve(id) {
    try {
        const res = await fetch(`${BASE_URL}/admin/ngos/${id}/approve`, {
            method: "PUT"
        });

        if (res.ok) {
            alert("Approved Successfully");
            loadPending(); // refresh
        } else {
            alert("Approve failed");
        }

    } catch (err) {
        console.error(err);
        alert("Error approving NGO");
    }
}

// ✅ REJECT
async function reject(id) {
    try {
        const res = await fetch(`${BASE_URL}/admin/ngos/${id}/reject`, {
            method: "PUT"
        });

        if (res.ok) {
            alert("Rejected Successfully");
            loadPending(); // refresh
        } else {
            alert("Reject failed");
        }

    } catch (err) {
        console.error(err);
        alert("Error rejecting NGO");
    }
}

// ✅ DELETE
async function deleteNGO(id) {

    const confirmDelete = confirm("Are you sure?");
    if (!confirmDelete) return;

    try {
        const res = await fetch(`${BASE_URL}/admin/ngos/${id}`, {
            method: "DELETE"
        });

        if (res.ok) {
            alert("Deleted Successfully");

            // reload correct tab
            if (currentView === "approved") loadApproved();
            else if (currentView === "rejected") loadRejected();
            else loadPending();

        } else {
            alert("Delete failed");
        }

    } catch (err) {
        console.error(err);
        alert("Error deleting NGO");
    }
}
// ✅ LOGOUT
function logout() {
    localStorage.removeItem("user");

    alert("Logged out successfully");

    window.location.href = "login.html";
}
// ✅ ADMIN WELCOME
function setWelcome() {
    document.getElementById("welcomeText").innerText =
        "Hello Admin, Welcome Back 👋";
}

// ✅ AUTO LOAD ON PAGE OPEN
window.onload = () => {
    setWelcome();   // 👈 greeting
    loadPending();  // 👈 existing functionality
};