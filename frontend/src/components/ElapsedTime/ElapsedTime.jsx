export const ElapsedTime = ({ createdAt }) => {
  const minute = 60 * 1000; // 1분
  const hour = 60 * minute; // 1시간
  const day = 24 * hour; // 1일
  const week = 7 * day; // 1주일

  const currentDate = new Date();
  const createdDate = new Date(createdAt);
  const elapsedTime = currentDate - createdDate;
  let result = '';

  if (elapsedTime < week) {
    if (elapsedTime < hour) {
      const minutes = Math.floor(elapsedTime / minute);
      result = minutes + '분 전';
    } else if (elapsedTime < day) {
      const hours = Math.floor(elapsedTime / hour);
      result = hours + '시간 전';
    } else {
      const days = Math.floor(elapsedTime / day);
      result = days + '일 전';
    }
  } else {
    if (!isNaN(createdDate.getTime())) {
      const editedDateString = createdDate.toLocaleDateString();
      result = `작성된 날짜: ${editedDateString}`;
    } else {
      result = '작성일 없음';
    }
  }

  return <div>{result}</div>;
};
