import React, { Component } from 'react'
import { Bar } from 'react-chartjs-2'
import ToDoAPI from './ToDoAPI'
import { Redirect } from 'react-router'
import { NavLink } from 'react-router-dom'

class Report extends Component {
    constructor(props) {
        super(props)

        let admintoken = sessionStorage.getItem("admintoken")
        this.state = {
            reports: {},
            adminlog: admintoken
        }
    }
    componentDidMount() {
        if(this.state.adminlog){
        let allreport = []
        ToDoAPI.Report()
            .then(res => {
                if (res.status === 200) {
                    allreport = res.data
                }
                let data = {
                    labels: ['Active Users', 'Deactivated Users', 'Total Task'],
                    datasets: [
                        {
                            label: 'Total Activity in Last 7 Days',
                            data: allreport,
                            borderColor: ['rgba(159,80,223,0.4)', 'rgba(199,20,125,0.4)', 'rgba(36,154,210,0.4)'],
                            backgroundColor: ['rgba(159,80,223,0.5)', 'rgba(199,20,125,0.4)', 'rgba(36,154,210,0.4)']
                        }
                    ]
                }

                this.setState({ reports: data })
            })
            .catch(error => {
                alert("Something Wrong")
                console.log(error)
            })
        }
        
    }
    option = {
        title: {
            display: true,
            text: 'Report'
        },
        scales: {
            yAxes: [
                {
                    ticks: {
                        beginAtZero:true,
                        stepSize: 1
                    }
                }
            ]
        }
    }

    render() {
        if(this.state.adminlog){

        return (
            <div className="chart">
                <NavLink to="/admin" className="btn btn-primary" />
                <Bar data={this.state.reports} options={this.option} />
            </div>
        )
        }
        else{
           return <Redirect to="/" />
        }
    }
}

export default Report;
