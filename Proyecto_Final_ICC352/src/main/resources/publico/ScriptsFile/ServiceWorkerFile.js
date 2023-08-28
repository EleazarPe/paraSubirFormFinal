const CACHE_NAME = 'my-cache-v1';
const urlsToCache = [
    'assets/bootstrap/css/bootstrap.min.css',
    'assets/css/Nunito.css',
    'assets/fonts/fontawesome-all.min.css',
    'assets/bootstrap/js/bootstrap.min.js',
    'assets/fonts/fa-brands-400.eot',
    'assets/fonts/fa-brands-400.svg',
    'assets/fonts/fa-brands-400.ttf',
    'assets/fonts/fa-brands-400.woff',
    'assets/fonts/fa-brands-400.woff2',
    'assets/fonts/fa-regular-400.eot',
    'assets/fonts/fa-regular-400.svg',
    'assets/fonts/fa-regular-400.ttf',
    'assets/fonts/fa-regular-400.woff',
    'assets/fonts/fa-regular-400.woff2',
    'assets/fonts/fa-solid-900.eot',
    'assets/fonts/fa-solid-900.svg',
    'assets/fonts/fa-solid-900.ttf',
    'assets/fonts/fa-solid-900.woff',
    'assets/fonts/fa-solid-900.woff2',
    'assets/fonts/fontawesome-all.min.css',
    'assets/fonts/XRXV3I6Li01BKofIMeaBXso.woff2',
    'assets/fonts/XRXV3I6Li01BKofINeaB.woff2',
    'assets/fonts/XRXV3I6Li01BKofIO-aBXso.woff2',
    'assets/fonts/XRXV3I6Li01BKofIOOaBXso.woff2',
    'assets/fonts/XRXV3I6Li01BKofIOuaBXso.woff2',
    'assets/fonts/XRXX3I6Li01BKofIMNaDRs4.woff2',
    'assets/fonts/XRXX3I6Li01BKofIMNaHRs71cA.woff2',
    'assets/fonts/XRXX3I6Li01BKofIMNaMRs71cA.woff2',
    'assets/fonts/XRXX3I6Li01BKofIMNaNRs71cA.woff2',
    'assets/fonts/XRXX3I6Li01BKofIMNaORs71cA.woff2',
    'assets/js/bs-init.js',
    'assets/js/chart.min.js',
    'assets/js/theme.js',
    '/table.html'






    // Otros recursos que quieras cachear
];

self.addEventListener('install', event => {
    event.waitUntil(
        caches.open(CACHE_NAME)
            .then(cache => cache.addAll(urlsToCache))
    );
});

self.addEventListener('fetch', event => {
    event.respondWith(
        caches.match(event.request)
            .then(response => {
                return response || fetch(event.request);
            })
    );
});