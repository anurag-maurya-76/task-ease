class Formatter {
  date(timeStamp: number) {
    const month: Record<number, string> = {
      1: "Jan",
      2: "Feb",
      3: "Mar",
      4: "Apr",
      5: "May",
      6: "Jun",
      7: "Jul",
      8: "Aug",
      9: "Sep",
      10: "Oct",
      11: "Nov",
      12: "Dec",
    };
    const date = new Date(timeStamp);
    const yyyy = date.getFullYear();
    const mm = date.getMonth() + 1;
    const dd = date.getDate();
    const formattedDate: string =
      (dd < 10 ? "0" : "") + dd + ", " + month[mm] + " " + yyyy;
    return { formattedDate };
  }
  getDay(index: number) {
    const day: Record<number, string> = {
      0: "M",
      1: "T",
      2: "W",
      3: "T",
      4: "F",
      5: "S",
      6: "S",
    };
    return day[index];
  }
}

const formatter = new Formatter();
export default formatter;
