import styles from "./TaskList.module.scss";
import TaskCard from "../../reusable/TaskCard";
import AddTask from "../AddTask";
import formatter from "../../utils/formatter";
import { greeter } from "../../utils/greeter";
import { motion } from "framer-motion";
import { useFetchTaskList } from "../../hooks/useFetchTaskList";

const TaskList = () => {
  const { taskList, isLoading } = useFetchTaskList();
  const currentDate = formatter.date(Date.now()).formattedDate;

  return (
    <div className={styles.taskList}>
      <div className={styles.taskList__header}>
        <div className={styles.taskList__header__title}>{greeter()}</div>
        <div className={styles.taskList__header__subtitle}>
          What's your plan for today?
        </div>
        <div className={styles.taskList__header__date}>{currentDate}</div>
      </div>
      <div className={styles.taskList__addTask}>
        <AddTask />
      </div>
      <div className={styles.taskList__body}>
        {!isLoading &&
          taskList &&
          taskList.map((task: any, key: number) => {
            return (
              <motion.div
                key={key}
                initial={{ y: -50, opacity: 0, width: "100%" }}
                animate={{
                  y: 0,
                  opacity: 1,
                }}
                transition={{
                  delay: key * 0.1,
                }}
              >
                <TaskCard task={task} />;
              </motion.div>
            );
          })}
      </div>
    </div>
  );
};

export default TaskList;
