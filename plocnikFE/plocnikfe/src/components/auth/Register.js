import React, { Component } from 'react';
import { connect } from 'react-redux';
import "../css/Register.css";
import  {userActions}  from "../actions/userActions";
import { Link } from 'react-router-dom';

class Register extends Component {
    constructor(props){
        super(props);
        this.state = {
            user: {
                korisnickoIme: '',
                email: '',
                zaporka2:'',
                zaporka: ''
            },
            submitted: false
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

  handleChange(e) {
    const { name, value } = e.target;
    const { user } = this.state;
    this.setState({
        user: {
            ...user,
            [name]: value
        }
    });
  }
  handleSubmit (e) {
    e.preventDefault();
    this.setState({ submitted: true });
        const { user } = this.state;
        if (user.korisnickoIme && user.email && (user.zaporka ==user.zaporka2)) {
            this.props.register(user);
        }
  }

  render() {
    const { registering } = this.props;
    const {error} = this.props;
    const { user, submitted } = this.state;
    return (
      <div className="container-auth">
        <div class=" z-depth-6 card small  grey darken-3">
            <div className="card-content">
                <form className=" grey darken-3" onSubmit={this.handleSubmit}>
                <h5 className="">Registracija</h5>

                <div className={'form-group' + (submitted && !user.email ? ' has-error' : '')}>
                    <i class="material-icons blueIcon">mail_outline</i>
                    <label htmlFor="email">Email</label>
                    <input type="email" className="form-control" name="email" value={user.email} onChange={this.handleChange} />
                                {submitted && !user.email &&
                                    <div className="help-block red-text">Email je obavezan!</div>
                                }
                    
                </div>

                <div className={'form-group' + (submitted && !user.korisnickoIme ? ' has-error' : '')}>
                <i class="material-icons blueIcon">account_circle</i>
                    <label htmlFor="korisnickoIme">Korisničko ime </label>
                    <input type="text" className="form-control" name="korisnickoIme" value={user.korisnickoIme} onChange={this.handleChange} />
                                {submitted && !user.korisnickoIme &&
                                    <div className="help-block red-text">Korisničko ime je obavezno</div>
                                }
                </div> 

                <div className={'form-group' + (submitted && !user.zaporka ? ' has-error' : '')}>
                <i class="material-icons blueIcon">lock_outline</i>
                    <label htmlFor="zaporka">Zaporka</label>
                    <input type="password" className="form-control" name="zaporka" value={user.zaporka} onChange={this.handleChange} />
                                {submitted && !user.zaporka &&
                                    <div className="help-block red-text">Zaporka je obavezna</div>
                                }
                </div>
                <div className={'form-group' + (submitted && !user.zaporka2 ? ' has-error' : '')}>
                <i class="material-icons blueIcon">replay</i>
                    <label htmlFor="zaporka2">Ponovi zaporku</label>
                    <input type="password" className="form-control" name="zaporka2" value={user.zaporka2} onChange={this.handleChange} />
                                {submitted && (user.zaporka!=user.zaporka2) &&
                                    <div className="help-block red-text">Zaporke se ne podudaraju!</div>
                                }
                </div>
                
                <div className="input-field">
                    <button className="btn">Registracija</button>
                    {registering}
                    <div className="center red-text">
                    { error ? <p>{error.message}</p> : null }
                    </div> 
                </div>
                </form>
                <div>
                    Već imaš račun? <Link to={'/login'}>Prijavi se. </Link>
                </div>
            </div>
        </div>
      </div>
    )
  }
}

function mapState(state) {
    const { registering } = state.registration;
    return { registering, error:state.alert};
}

const actionCreators = {
    register: userActions.register
}

const connectedRegisterPage = connect(mapState, actionCreators)(Register);
export default connectedRegisterPage ;
