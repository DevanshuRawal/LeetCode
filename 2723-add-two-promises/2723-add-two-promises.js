/**
 * @param {Promise} promise1
 * @param {Promise} promise2
 * @return {Promise}
 */
var addTwoPromises = async function(promise1, promise2) {
    const num1 = await promise1;
    const num2 = await promise2;

    return num1 + num2;
};