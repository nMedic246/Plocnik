
import { authHeader } from '../helpers/authHeader';
import {history} from '../helpers/history';

export const userService = {
    login,
    logout,
    register,
    getUserByUsername,
    getUserImg,
    getUserWishList,
    getUserCollection,
    updateKorisnik,
    getBiggestRecordOwners,
    obrisiProfil
   };

function login(korisnickoIme, zaporka) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ korisnickoIme, zaporka })
    };

    return fetch(`/login`, requestOptions)
        .then(handleResponse)
        .then(user => {
            // store user details and jwt token in local storage to keep user logged in between page refreshes
            user.authdata = window.btoa(korisnickoIme+':'+zaporka);
            user.isAdmin = korisnickoIme==='admin';
            localStorage.setItem('user', JSON.stringify(user));
            console.log(localStorage.user);
            return user;
        });
}

function logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('user');
}

function register(user) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    };

    return fetch('/korisnik/register', requestOptions).then(handleResponse);
}

function obrisiProfil(korisnickoIme){
    const requestOptions={
        method:'DELETE',
        headers:authHeader()
    };
    return fetch('/korisnik/'+korisnickoIme+'/obrisiProfil',requestOptions);
}

function updateKorisnik(userN,korisnickoIme){
    const requestOptions = {
        method: 'PUT',
        headers:authHeader(),
        body: JSON.stringify(userN)
    };

    return fetch(`/korisnik/updateKorisnik/`+korisnickoIme, requestOptions)
}
function getUserByUsername(korisnickoIme){
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
    };

    return fetch(`/korisnik/`+korisnickoIme, requestOptions);
}

function getUserImg(korisnickoIme){
    const requsetOptions = {
        method:'GET'
    }
    return fetch('/korisnik/slika/'+korisnickoIme,requsetOptions);
 }

function getUserCollection(korisnickoIme){
    const requsetOptions = {
        method:'GET'
    }
    return fetch('/korisnik/'+korisnickoIme+'/popisPloca',requsetOptions);
}
function getBiggestRecordOwners(){
    const requsetOptions = {
        method:'GET'
    }
    return fetch('/korisnik/najveciKolekcionari',requsetOptions);
}

function getUserWishList(korisnickoIme){
    const requsetOptions = {
        method:'GET'
    }
    return fetch('/korisnik/'+korisnickoIme+'/listaZelja',requsetOptions);
}
function handleResponse(response) {
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        console.log(data)
        if (!response.ok) {
            if (response.status === 401) {
                // auto logout if 401 response returned from api
                logout();
                window.location.reload(true);
            }
            
            const error = (data && data.message) || response.statusText;
            console.log(error)
            return Promise.reject(error);
        }

        return data;
    });
}