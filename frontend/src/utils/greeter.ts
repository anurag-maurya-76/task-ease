export const greeter = () => {
  let today = new Date();
  if (today.getHours() < 12) {
    return "Good Morning";
  } else if (today.getHours() < 16) {
    return "Good Afternoon";
  } else {
    return "Good Evening";
  }
};
