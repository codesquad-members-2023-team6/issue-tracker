export const removeEmptyKeyValues = (object) => {
  Object.entries(object).forEach((set) => {
    const [key, value] = set;
    if (!value) delete object[key];
  });
  return object;
};

export const deepCopy = (value) => {
  return JSON.parse(JSON.stringify(value));
};

export function debounce(callback, delay) {
  let timerId;
  return (...args) => {
    if (timerId) clearTimeout(timerId);
    timerId = setTimeout(() => callback(...args), delay);
  };
}
