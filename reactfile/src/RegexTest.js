import React, { Component } from 'react'

class RegexTest extends Component {

    constructor(props) {
        super(props)
    
        this.state = {
             email:''
        }
    }
    handle =(e) =>{
        this.setState({email:e.target.value})
    }
    submit = (e)=>{
        e.preventDefault()
        let pass=/^[A-Z]{1,3}[a-z]{1,3}.[0-9]{1,5}$/; 
        let pattern=/^[a-zA-Z]{1}[a-zA-Z0-9]*@([a-zA-Z]+\.)+[a-zA-Z]+$/;
        if(pass.test(this.state.email)){
            alert("everything correct")
            console.log(this.state.email)
        }
        else{
            alert("email not correct")
        }
    }

    render() {
        return (
            <div>
                <form onSubmit={this.submit}>
                    <input type="text" value={this.state.email} onChange={this.handle}></input>
                    <input type="submit" value="submit"></input>
                </form>
            </div>
        )
    }
}

export default RegexTest
