export const convertListToOptions = (list, contentsKey) => {
  return list.map((element) => {
    const option = {};
    if (element.profileImageUrl) {
      option.profileImageUrl = element.profileImageUrl;
    }

    option.contents = element[contentsKey];

    if (element.index === parseInt(-1)) {
      option.index = element.index;
    }

    option.index = element.index;
    return option;
  });
};
