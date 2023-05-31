export const convertListToOptions = (list, contentsKey) => {
  return list.map((element) => {
    const index =
      element.milestoneIdx ??
      element.labelIdx ??
      element.memberIdx ??
      element.index;

    const option = {};
    if (element.profileImageUrl) {
      option.profileImageUrl = element.profileImageUrl;
    }

    option.contents = element[contentsKey];

    if (index === parseInt(-1)) {
      option.index = index;
    }

    option.index = index;

    return option;
  });
};
