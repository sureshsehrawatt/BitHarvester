import React from "react";

const TreeTable = ({ data }) => {
  return (
    <div>
      <table border="1" color="red">
        <tbody>
          {data != null &&
            Object.entries(data).map(([key, value]) =>
              typeof value === "object" ? (
                <tr key={key}>
                  <td>{key}</td>
                  <td>
                    <TreeTable data={value} />
                  </td>
                </tr>
              ) : (
                <tr key={key}>
                  <td>{key}</td>
                  <td>{value}</td>
                </tr>
              )
            )}
        </tbody>
      </table>
    </div>
  );
};

export default TreeTable;
