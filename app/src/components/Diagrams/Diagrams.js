import React from 'react';
import {Tooltip, XAxis, YAxis, Legend, CartesianGrid, Bar, Line, ComposedChart,} from "recharts";
import "./Diagrams.css"

const Diagrams = () => {
  const data = [
    { name: "Poniedziałek", kcal: 2700 , goal: 2500},
    { name: "Wtorek", kcal: 1999 , goal: 2500 },
    { name: "Środa", kcal: 2220 , goal: 2500},
    { name: "Czwartek", kcal: 2350, goal: 2500 },
    { name: "Piątek", kcal: 1436 , goal: 2500},
    { name: "Sobota", kcal:  2074, goal: 2500},
    { name: "Niedziela", kcal: 2113 , goal: 2500},
  ];

  return (
    <div className="box">
      <h1 className="title">Dzienne spożycie kcal:</h1>
      <div className="Diagrams">
      <ComposedChart
          width={600}
          height={300}
          data={data}
          margin={{
            top: 20,
            right: 60,
            left: 10,
            bottom: 5,
          }}
          barSize={20}
        >
          <XAxis
            dataKey="name"
            scale="point"
            padding={{ left: 10, right: 10}}
          />
          <YAxis />
          <Tooltip />
          <Legend />
          <CartesianGrid strokeDasharray="3 3" />
          <Bar dataKey="kcal" fill="#8884d8" background={{ fill: "#eee" }} />
          <Line type="monotone" dataKey="goal" stroke="#ff7300" />
          </ComposedChart>
          
      </div>
    </div>
  );
};
export default Diagrams;                              