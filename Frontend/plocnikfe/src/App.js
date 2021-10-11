import React, { Component } from 'react';
import {Router, Switch, Route} from 'react-router-dom';
import Navbar from "./components/layout/Navbar";
import Ploce from "./components/ploce/Ploce.js";
import IzvodacList from "./components/izvodaci/IzvodacList";
import Login from "./components/auth/Login";
import Register from "./components/auth/Register";
import { connect } from 'react-redux';
import { alertActions } from "./components/actions/alertActions";
import { history } from './components/helpers/history';
import izdavackaKuca from './components/izdavackekuce/izdavackaKuca';
import Pocetna from "./components/frontpage/Pocetna";
import vinylPloca from './components/ploce/vinylPloca';
import izvodac from './components/izvodaci/izvodac';
import kuceList from './components/izdavackekuce/kuceList';
import korisnikPage from './components/korisnik/korisnikPage';
import addPloca from './components/admin/addPloca'
import addIzdavackaKuca from'./components/admin/addIzdavackaKuca';
import addIzvodac from './components/admin/addIzvodac';
import editProfile from './components/korisnik/editProfile'

class App extends Component {
  constructor(props){
    super(props);
    history.listen((location, action) => {
      this.props.clearAlerts();
  });
  }

  render() {
    return (
      <Router history={history}>
        <div className="App">
          <Navbar/>
          <Switch>
            <Route path='/' exact component={Pocetna}/>
            <Route path ='/izvodac/:id' component={izvodac}/>
            <Route path='/vinylPloca/:id' component={vinylPloca}/>
            <Route path='/izdavackaKuca/:id' component={izdavackaKuca}/>
            <Route path='/korisnik/:korisnickoIme' component = {korisnikPage}/>
            <Route path='/urediProfil/:korisnickoIme' component={editProfile}/>
            <Route path ='/listaIzdavackihKuca' component={kuceList}/>
            <Route path ='/listaIzvodaca' component={IzvodacList} />
            <Route path ='/listaPloca' component={Ploce} />
            <Route path= '/login' component={Login}/>
            <Route path= '/register' component ={Register}/>
            <Route path='/admin/dodajPlocu' component={addPloca}/>
            <Route path='/admin/dodajIzdavackuKucu' component={addIzdavackaKuca}/>
            <Route path='/admin/dodajIzvodaca' component={addIzvodac}/>
          
          </Switch>
        </div>
      </Router>
    );
  }
}

function mapState(state) {
  const { alert } = state;
  return { alert };
}

const actionCreators = {
  clearAlerts: alertActions.clear
};

const connectedApp = connect(mapState, actionCreators)(App);
export default  connectedApp ;