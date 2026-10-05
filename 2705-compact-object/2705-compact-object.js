var compactObject = function(obj) {
    if (Array.isArray(obj)) {
        return obj
            .filter(Boolean)
            .map(compactObject);
    }

    if (typeof obj === "object" && obj !== null) {
        const result = {};

        for (const key in obj) {
            if (Boolean(obj[key])) {
                result[key] = compactObject(obj[key]);
            }
        }

        return result;
    }

    return obj;
};