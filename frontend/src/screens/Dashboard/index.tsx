import styles from "./Dashboard.module.scss";
import TaskMap from "../../components/TaskMap";
import TaskCalendar from "../../components/TaskCalendar";
import TaskList from "../../components/TaskList";

const Dashboard = () => {
  return (
    <div className={styles.dashboard}>
      <TaskMap />
      <TaskList />
      <TaskCalendar />
    </div>
  );
};

export default Dashboard;
