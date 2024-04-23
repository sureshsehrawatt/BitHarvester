import React, { useContext } from "react";
import TreeTable from "../../../rough/TreeTable";
import DataContext from "./DataContext";

const Dashboard = () => {
  const { data } = useContext(DataContext);

  return (
    <div>{data != null ? <TreeTable data={data} /> : <p>Welcome!</p>}</div>
  );
};

export default Dashboard;
