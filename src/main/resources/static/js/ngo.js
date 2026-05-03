const BASE_URL = "/api";

const ngoForm = document.getElementById("ngoForm");

if (ngoForm) {

    ngoForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const formData = new FormData();

        formData.append("name", document.getElementById("name").value);
        formData.append("email", document.getElementById("email").value);
        formData.append("password", document.getElementById("password").value);

        formData.append("phone", document.getElementById("phone").value);
        formData.append("address", document.getElementById("address").value);

        formData.append("state", document.getElementById("state").value);

        formData.append("registrationNumber", document.getElementById("reg").value);

        formData.append("description", document.getElementById("desc").value);

        formData.append("contactPersonName", document.getElementById("contact").value);

        formData.append("govtCertificate", document.getElementById("govt").files[0]);
        formData.append("panCard", document.getElementById("pan").files[0]);

        try {
            const res = await fetch(`${BASE_URL}/ngos/register`, {
                method: "POST",
                body: formData
            });

            if (res.ok) {
                alert("NGO Registered Successfully. Please login.");
                window.location.href = "login.html";
            } else {
                const text = await res.text();
                alert(text || "Error");
            }

        } catch (err) {
            console.error(err);
            alert("Server error");
        }
    });
}


/* =======================
   👁 TOGGLE PASSWORD (ADD HERE)
======================= */
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