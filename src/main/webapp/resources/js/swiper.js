// Initialize Swiper
var swiper = new Swiper('.cardSwiper', {
    speed: 400,
    slidesPerView: 'auto',
    spaceBetween: 30,
    autoplay: {
        delay: 5000,
        disableOnInteraction: false,
    },
    loop: true,
    breakpoints: {
        320: {
            slidesPerView: 1,
            spaceBetween: 20,
        },
        800: {
            slidesPerView: 2,
            spaceBetween: 20,
        },
        1200: {
            slidesPerView: 2,
            spaceBetween: 20,
        },
    },
});

// break point decide how many slide you need on each view
