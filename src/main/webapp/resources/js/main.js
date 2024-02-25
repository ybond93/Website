// Navigation button onclick event and change active button
window.addEventListener('load', () => {
    let navList = document.querySelectorAll('.nav-btn');
    navList.forEach(nav =>
        nav.addEventListener('click', function (e) {
            e.preventDefault();
            navList.forEach(function (el) {
                el.classList.remove('active');
            });
            this.classList.add('active');
        })
    );
});

// Back to Top
const scrollToTop = () => {ö
    window.scrollTo(0, 0);
};

// Show Back to top button on page scroll
const selectHeader = document.querySelector('#header');
const backToTop = document.querySelector('.back-to-top');
if (selectHeader) {
    const headerScrolled = () => {
        if (window.scrollY > 100) {
            backToTop.classList.add('active');
        } else {
            backToTop.classList.remove('active');
        }
    };
    window.addEventListener('load', headerScrolled);
    document.addEventListener('scroll', headerScrolled);
}

/**
 * Animation on scroll - Initialization
 */
window.addEventListener('load', () => {
    AOS.init({
        duration: 1000,
        easing: 'ease-in-out',
        once: true,
        mirror: false,
    });
});

function setFormMessage(formElement, type, message) {
    const messageElement = formElement.querySelector(".form__message");

    messageElement.textContent = message;
    messageElement.classList.remove("form__message--success", "form__message--error");
    messageElement.classList.add(`form__message--${type}`);
}

function setInputError(inputElement, message) {
    inputElement.classList.add("form__input--error");
    inputElement.parentElement.querySelector(".form__input-error-message").textContent = message;
}

function clearInputError(inputElement) {
    inputElement.classList.remove("form__input--error");
    inputElement.parentElement.querySelector(".form__input-error-message").textContent = "";
}

document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.querySelector("#login");
    const createAccountForm = document.querySelector("#createAccount");

    document.querySelector("#linkCreateAccount").addEventListener("click", e => {
        e.preventDefault();
        loginForm.classList.add("form--hidden");
        createAccountForm.classList.remove("form--hidden");
    });

    document.querySelector("#linkLogin").addEventListener("click", e => {
        e.preventDefault();
        loginForm.classList.remove("form--hidden");
        createAccountForm.classList.add("form--hidden");
    });

    loginForm.addEventListener("submit", e => {
        e.preventDefault();

        // Perform your AJAX/Fetch login

        setFormMessage(loginForm, "error", "Invalid username/password combination");
    });

    document.querySelectorAll(".form__input").forEach(inputElement => {
        inputElement.addEventListener("blur", e => {
            if (e.target.id === "signupUsername" && e.target.value.length > 0 && e.target.value.length < 10) {
                setInputError(inputElement, "Username must be at least 10 characters in length");
            }
        });

        inputElement.addEventListener("input", e => {
            clearInputError(inputElement);
        });
    });
});


function openPopup() {
    let popup = document.getElementById('popup'); // Assuming 'popup' is the ID of your single popup
    let overlay = document.getElementById('popup-background');

    if (popup && overlay) {
        popup.classList.add("open-popup"); // This class should handle visibility and transform
        overlay.style.display = 'block'; // Show the overlay
    }
}

function closePopup() {
    let popup = document.getElementById('popup'); // Assuming 'popup' is the ID of your single popup
    let overlay = document.getElementById('popup-background');

    if (popup && overlay) {
        popup.classList.remove("open-popup"); // This class should handle hiding the popup
        overlay.style.display = 'none'; // Hide the overlay
    }
}
