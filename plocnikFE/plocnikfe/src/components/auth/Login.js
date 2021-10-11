 
 import React, { Component } from 'react'
 import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import "../css/Login.css";

import  {userActions}  from "../actions/userActions";

 class Login extends Component {
     constructor(props){
        super(props);

        // reset login status
        this.props.logout();

        this.state = {
            korisnickoIme: '',
            zaporka: '',
            submitted:false
          }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
     }
   
   handleChange (e) {
    const { name, value } = e.target;
    this.setState({ [name]: value });
   }

   handleSubmit(e) {
     e.preventDefault();
     this.setState({ submitted: true });
        const {korisnickoIme, zaporka } = this.state;
        if (korisnickoIme && zaporka) {
            this.props.login(korisnickoIme, zaporka);
        }
     
   }

   render() {
    const { loggingIn } = this.props;
    const {error} = this.props;
    const { korisnickoIme, zaporka, submitted } = this.state;
     return (
       <div className="container-auth">
        <div className=" z-depth-6 card small grey darken-3">
          <div className="card-content">
            <form className="grey darken-3" onSubmit={this.handleSubmit}>
              <h5 className="white-text">Prijava</h5>

              <div className={'form-group' + (submitted && !korisnickoIme ? ' has-error' : '')}>
              <i class="material-icons blueIcon">account_circle</i>
                <label htmlFor="korinickoIme">Korisničko ime</label>
                <input type="text" id='korisnickoIme' name="korisnickoIme" value ={korisnickoIme} onChange={this.handleChange} />
                {submitted && !korisnickoIme &&
                                <div className="help-block red-text">Korisničko ime je obavezno!</div>
                            }
              </div>

              <div className={'form-group' + (submitted && !zaporka ? ' has-error' : '')}>
              <i class="material-icons blueIcon">lock_outline</i>
                <label htmlFor="zaporka">Zaporka</label>
                <input type="password" className="form-control" name="zaporka" value={zaporka} onChange={this.handleChange} />
                            {submitted && !zaporka &&
                                <div className="help-block red-text">Zaporka je obavezna!</div>
                            }
              </div>
              

              <div className="input-field">
                <button className="btn">Prijava</button>
                {loggingIn}  
                <div className="center red-text">
                  { error ? <p>{error.message}</p> : null }
                </div>           
              </div>
            </form>
            <div>
              Nemaš račun? <Link to={'/register'}>Registriraj se. </Link>
            </div>
          </div>

         </div>
       </div>
     )
   }
 }

 function mapState(state) {
    const { loggingIn } = state.authentication;
    return {loggingIn, error:state.alert};
}

const actionCreators = {
    login: userActions.login,
    logout: userActions.logout
};

export default connect(mapState, actionCreators)(Login);


 
/*
 import React from 'react';
 import './Login.css';

 function Login(props){
    const [loginForm, setLoginForm] = React.useState({korisnickoIme:'', zaporka:''});
    const[error, setError] = React.useState('');

    function onChange(event){
        const {name, value} = event.target;
        setLoginForm(oldForm =>({...oldForm, [name]: value}))
    }
  
    function onSubmit(e){
        e.preventDefault();
        setError("");
       /* const body= `korisnickoIme=${loginForm.korisnickoIme}&zaporka=${loginForm.zaporka}`;*//*
       const data = {
            korisnickoIme: loginForm.korisnickoIme,
            zaporka: loginForm.zaporka
        };
        const options={
            method:'POST',
            headers: {
                'Content-Type': 'application/json'
                /*'Content-Type': 'application/x-www-form-urlencoded'*/
           /* },
            body: JSON.stringify(data)
        };
        fetch('/login',options)
            .then(response =>{
                if(response.status ===400){
                    setError("Login failed");
                }else{
                    props.onLogin();
                    props.history.push('')
                }
            });
    }

    return (
        <div className="Login">
            <form onSubmit={onSubmit}>
                <div className="FormRow">
                    <label>Korisničko ime :</label>
                    <input name='korisnickoIme' onChange={onChange} value={loginForm.korisnickoIme}/>
                </div>
                <div className="FormRow">
                    <label>Zaporka: </label>
                    <input type='password' name='zaporka' onChange={onChange} value={loginForm.zaporka}/>
                </div>
                <div className='error'>{error}</div>
                <button type="submit">Login</button>
            </form>
        </div>
    )
 }

 export default Login*/