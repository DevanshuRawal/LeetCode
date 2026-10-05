/**
 * @param {Function} fn
 * @param {Array} args
 * @param {number} t
 * @return {Function}
 */
var cancellable = function(fn, args, t) {
    // Call immediately
    fn(...args);

    // Call repeatedly after every t milliseconds
    const timer = setInterval(() => {
        fn(...args);
    }, t);

    // Return cancel function
    return function() {
        clearInterval(timer);
    };
};