import React from "react";

const TreeTable = ({ data }) => {
  return (
    <div>
      <table style={{ borderCollapse: "collapse", width: "100%" }}>
        <tbody>
          {data != null &&
            Object.entries(data).map(([key, value]) =>
              typeof value === "object" ? (
                <tr key={key}>
                  <td style={{ border: "1px solid #ccc", padding: "8px" }}>
                    {key}
                  </td>
                  <td style={{ border: "1px solid #ccc", padding: "8px" }}>
                    <TreeTable data={value} />
                  </td>
                </tr>
              ) : (
                <tr key={key}>
                  <td style={{ border: "1px solid #ccc", padding: "8px" }}>
                    {key}
                  </td>
                  <td style={{ border: "1px solid #ccc", padding: "8px" }}>
                    {value}
                  </td>
                </tr>
              )
            )}
        </tbody>
      </table>
    </div>
  );
};

export default TreeTable;
