const BASE_URL = "/api";

const user = JSON.parse(localStorage.getItem("user"));

// ✅ LOAD PROFILE
function loadProfile() {

    document.getElementById("profile").innerHTML = `
        <p><b>Name:</b> ${user.name}</p>
        <p><b>Email:</b> ${user.email}</p>
        <p><b>Phone:</b> ${user.phone || "Not added"}</p>
    `;

    document.getElementById("editName").value = user.name;
    document.getElementById("editPhone").value = user.phone || "";
}

// ✅ SHOW EDIT
function showEdit() {
    document.getElementById("editSection").style.display = "block";
    document.getElementById("passwordSection").style.display = "none";
}

// ✅ SHOW PASSWORD
function showPassword() {
    document.getElementById("passwordSection").style.display = "block";
    document.getElementById("editSection").style.display = "none";
}

// ✅ UPDATE PROFILE
async function updateProfile() {

    const name = document.getElementById("editName").value;
    const phone = document.getElementById("editPhone").value;

    const res = await fetch(
        `${BASE_URL}/auth/update-profile?id=${user.id}&name=${name}&phone=${phone}`,
        { method: "PUT" }
    );

    if (res.ok) {
        const updatedUser = await res.json();

        localStorage.setItem("user", JSON.stringify(updatedUser));

        alert("Profile updated");

        loadProfile();
        document.getElementById("editSection").style.display = "none";

    } else {
        alert("Update failed");
    }
}

// ✅ CHANGE PASSWORD
async function changePassword() {

    const oldPass = document.getElementById("oldPass").value;
    const newPass = document.getElementById("newPass").value;

    if (!oldPass || !newPass) {
        alert("Enter both fields");
        return;
    }

    const res = await fetch(
        `${BASE_URL}/auth/change-password?email=${user.email}&oldPassword=${oldPass}&newPassword=${newPass}`,
        { method: "PUT" }
    );

    const text = await res.text();

    if (res.ok) {
        alert(text);

        document.getElementById("oldPass").value = "";
        document.getElementById("newPass").value = "";
        document.getElementById("passwordSection").style.display = "none";

    } else {
        alert(text);
    }
}

// ✅ BACK
function goBack() {
    window.location.href = "user-dashboard.html";
}

// ✅ LOGOUT FUNCTION (NEW)
function logout() {

    // clear user session
    localStorage.removeItem("user");
    localStorage.removeItem("ngoId");

    alert("Logged out successfully");

    // redirect to login page
    window.location.href = "login.html";
}
function togglePassword(id, icon) {

    const input = document.getElementById(id);

    if (input.type === "password") {
        input.type = "text";
        icon.textContent = "🙈";
    } else {
        input.type = "password";
        icon.textContent = "👁";
    }
}

window.onload = loadProfile;