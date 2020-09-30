import React, { useState, useEffect } from 'react'
import { Bar } from 'react-chartjs-2';
import ToDoAPI from './ToDoAPI';



function Report22() {
    const [chartdata, setchartdata] = useState()

    const chart = () => {
        let allreport = [];

        ToDoAPI.Report()
            .then(res => {
                if (res.status === 200) {
                    allreport = res.data
                    console.log(allreport)
                }
                setchartdata({
                    labels: ['Active Users', 'New Users', 'Total Task'],
                    datasets: [
                        {
                            label: 'Total Activity in Last 7 Days',
                            data: allreport,
                            borderColor: ['rgba(159,80,223,0.4)', 'rgba(199,20,125,0.4)', 'rgba(36,154,210,0.4)'],
                            backgroundColor: ['rgba(159,80,223,0.5)', 'rgba(199,20,125,0.4)', 'rgba(36,154,210,0.4)']
                        }
                    ]
                })
            })
            .catch(error => {
                alert("Something Wrong")
                console.log(error)
            })
    }

    const option = {
        title: {
            display: true,
            text: 'Report'
        },
        scales: {
            yAxis: [
                {
                    ticks: {
                        min: 0,
                        max: 100,
                        stepSize: 5
                    }
                }
            ]
        }
    }
    useEffect(() => {
        chart()
    }, [])
    return (
        <div className="chart">
            <Bar data={chartdata} options={option} />
        </div>
    )
}

export default Report22;
