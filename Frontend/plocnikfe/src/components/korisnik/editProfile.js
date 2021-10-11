import { Component } from "react";
import {userService} from '../services/userService'
import {history} from '../helpers/history';
import {userActions} from "../actions/userActions"

class editProfile extends Component{
    constructor(props){
        super(props);
        console.log(props.match.params)
        let userL = JSON.parse(localStorage.getItem('user'));
        const isLoggedin = userL ? true:false
        this.state ={
            userT:userL,
            isLoggedin:isLoggedin,
            user:{
                korisnickoIme:userL.korisnickoIme,
                email:userL.email,
                zaporka:'',
                zaporka2:''
            },
            submitted:false,
            error:''
        }
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
        const { user ,userT,error} = this.state;
        
        
        if(user.zaporka ==user.zaporka2){
            if(user.zaporka.trim()==''){
                this.setState({
                    error:"Obavezna promjena zaporki pri uredivanju profila"
                })
            }else{
                userService.updateKorisnik(user,userT.korisnickoIme)
                .then(async response => {
                        const data = await response.json();
                        
                        if (!response.ok) {
                            const error = (data && data.message) || response.statusText;
                            this.setState({
                                error:error
                            })
                        }else{
                            data.authdata = window.btoa(data.korisnickoIme+':'+user.zaporka);
                            data.isAdmin = data.korisnickoIme==='admin';
                            localStorage.setItem('user', JSON.stringify(data));
                            history.push('/korisnik/'+data.korisnickoIme)
                        }
                       
                    })
                    .catch(error => {
                        this.setState({ errorM: error.toString() });
                        console.error('There was an error!', error);
                    });
            }
        
        }
          
    }

    deleteItem(e){
        e.preventDefault();
        const {korisnickoIme}=this.state.user;
        userService.obrisiProfil(korisnickoIme).then(res=>res.json())
        .then(res=>{
            console.log(res)
            userActions.logout()
            history.push('')
            window.location.reload(false);
        })
        
    }
    render(){
        const {user,userT,submitted,error}=this.state;
        var jeTrenutniKorisnik =false;
        if(user){
           jeTrenutniKorisnik = userT.korisnickoIme==this.props.match.params.korisnickoIme ? true:false;
        }

       if(jeTrenutniKorisnik){
           return(
            <div className="container-auth">
        <div class=" z-depth-6 card small  grey darken-3">
            <div className="card-content">
                <form className=" grey darken-3" onSubmit={this.handleSubmit}>
                <span><h5 className="">Uredi profil</h5></span>
                <button  class="btn waves-effect waves-light " onClick={(e) => { if (window.confirm('Jeste li sigurni da želite obrisati svoj profil?')) this.deleteItem(e) } }>
                    Obriši profil
                </button>
                <div>
                    <br/>
                    <h6>Email: {user.email}</h6>           
                </div>

                <div >
                <i class="material-icons blueIcon">account_circle</i>
                    <label htmlFor="korisnickoIme">Korisničko ime </label>
                    <input type="text" className="form-control" name="korisnickoIme" value={user.korisnickoIme} onChange={this.handleChange}/>
                               
                </div> 

                <div >
                <i class="material-icons blueIcon">lock_outline</i>
                    <label htmlFor="zaporka">Nova zaporka</label>
                    <input type="password" className="form-control" name="zaporka" value={user.zaporka} onChange={this.handleChange}/>
                               
                </div>
                <div >
                <i class="material-icons blueIcon">replay</i>
                    <label htmlFor="zaporka2">Ponovi novu zaporku</label>
                    <input type="password" className="form-control" name="zaporka2" value={user.zaporka2} onChange={this.handleChange} />
                                {submitted && (user.zaporka!=user.zaporka2) &&
                                    <div className="help-block red-text">Zaporke se ne podudaraju!</div>
                                }
                </div>
                
                <div className="input-field">
                    <button className="btn">Pohrani Promjene</button>
                    <div className="center red-text">
                    { error ? <p>{error}</p> : null }
                    </div> 
                </div>
                </form>
               
            </div>
        </div>
      </div>
           )
       }else{
           return(<div>Ne bi smjeli biti ovdje!</div>)
       }
    }
}

export default editProfile;