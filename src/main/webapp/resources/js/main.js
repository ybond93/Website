// Smooth scroll for navbar links
document.querySelectorAll('nav#navbar a').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
        e.preventDefault();
        document.querySelector(this.getAttribute('href')).scrollIntoView({
            behavior: 'smooth'
        });
    });
});

// Example function to open a popup - adjust as needed
function openPopup() {
    document.getElementById("popup").style.visibility = "visible";
    document.getElementById("popup-background").style.display = "block";
}

function closePopup() {
    document.getElementById("popup").style.visibility = "hidden";
    document.getElementById("popup-background").style.display = "none";
}

// Initialization code for interactive elements
document.addEventListener('DOMContentLoaded', function() {
    // Initialize any sliders, popups, etc.
});
window.onload = function() {
    // Function to hide all sections
    function hideAllSections() {
        document.querySelectorAll('.menu-section').forEach(function(section) {
            section.style.display = 'none';
        });
    }

    // Function to show a specific section
    function showSection(sectionId) {
        const section = document.getElementById(sectionId);
        if (section) {
            hideAllSections();
            section.style.display = 'block';
        }
    }

    // Add click event listeners to navbar links
    document.querySelectorAll('.navbar-nav .nav-link').forEach(function(link) {
        link.addEventListener('click', function(e) {
            e.preventDefault(); // Prevent default anchor behavior
            const sectionId = this.getAttribute('href').substring(1); // Get the section ID (removing the #)
            showSection(sectionId);
        });
    });

    // Initially hide all sections and show the first one
    hideAllSections();
    showSection('lunch'); // Show 'lunch' section by default on page load
};