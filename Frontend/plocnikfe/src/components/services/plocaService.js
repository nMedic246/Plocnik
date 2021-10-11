import { authHeader } from '../helpers/authHeader';
import {history} from '../helpers/history';

class plocaService{
 getPlocaById(id){
        const requestOptions ={
            method: 'GET',
            headers:{'Content-Type': 'application/json'}
        };
        return fetch('/vinylPloca/'+id, requestOptions);
 }

 getPlocaImg(id){
    const requsetOptions = {
        method:'GET'
    }
    return fetch('/vinylPloca/slika/'+id,requsetOptions);
 }
 getPlocaOwners(id){
    const requsetOptions = {
        method:'GET',
        headers: authHeader()
    }
    return fetch('/vinylPloca/imajuPlocu/'+id,requsetOptions);
 }

 getPlocaWanters(id){
    const requsetOptions = {
        method:'GET',
        headers: authHeader()
    }
   
    return fetch('/vinylPloca/zelePlocu/'+id,requsetOptions);
 }

 getPlocaPjesme(id){
    const requestOptions ={
        method: 'GET',
        headers:{'Content-Type': 'application/json'}
    };
    return fetch('/vinylPloca/popisPjesama/'+id, requestOptions);
 }
 getPlocaZanr(id){
    const requestOptions ={
        method: 'GET',
        headers:{'Content-Type': 'application/json'}
    };
    return fetch('/vinylPloca/zanr/'+id, requestOptions);
 }

selectImamPlocu(id){
    const requestOptions ={
        method: 'GET',
        headers: authHeader()
    };
    return fetch('/vinylPloca/dodajUMojePloce/'+id, requestOptions);
}
deselectImamPlocu(id){
    const requestOptions ={
        method: 'GET',
        headers: authHeader()
    };
    return fetch('/vinylPloca/ukloniIzMojihPloca/'+id, requestOptions);
}
selectZelimPlocu(id){
    const requestOptions ={
        method: 'GET',
        headers: authHeader()
    };
    return fetch('/vinylPloca/dodajUListuZelja/'+id, requestOptions);
}
deselectZelimPlocu(id){
    const requestOptions ={
        method: 'GET',
        headers: authHeader()
    };
    return fetch('/vinylPloca/ukloniIzListeZelja/'+id, requestOptions);
}

getPlocaReviews(id){
    const requestOptions = {
        method: 'GET',
        
    }
    return fetch('/recenzija/list/'+id,requestOptions);
}

getPlocasByNazivZanr(nazivZanr){
    const requestOptions = {
        method: 'GET',
    }
    return fetch('/zanr/'+nazivZanr,requestOptions);
}
getVrstePloca(){
    const requestOptions = {
        method: 'GET',
    }
    return fetch('/vrsta/list',requestOptions);
}
postReview(tekst,ocjena,korisnickoIme,id){
    console.log(authHeader())
    const requestOptions = {
        method: 'POST',
        headers: authHeader() ,
        body: JSON.stringify({ korisnickoIme,tekst,ocjena})
    };

    return fetch(`/recenzija/`+id+'/novaRecenzija', requestOptions);
}
dodajPlocu(nazivVinylPloca,godinaIzdanja,verzija,infoVinylPloca,idIzdavackaKuca,idIzvodac,idVrsta,idZanr){
    const requestOptions = {
        method: 'POST',
        headers: authHeader() ,
        body: JSON.stringify({nazivVinylPloca,godinaIzdanja,verzija,infoVinylPloca,idIzdavackaKuca,idIzvodac,idVrsta,idZanr })
    };

    return fetch('/vinylPloca/dodajPlocu', requestOptions);
}
obrisiPlocu(id){
    const requestOptions={
        method:'DELETE',
        headers:authHeader()
    };
    return fetch('/vinylPloca/'+id+'/obrisiVinylPlocu',requestOptions);
}

searchPloce(filter){
    const requestOptions={
        method:'GET',
    }
    if(filter.trim()==''){
        return  fetch('vinylPloca/list',requestOptions);
    }else{
        return fetch('vinylPloca/filter/'+filter.trim(),requestOptions);
    }
}
getMostPopular(){
    const requestOptions ={
        method: 'GET',
        headers:{'Content-Type': 'application/json'}
    };
    return fetch('/vinylPloca/najpopularnijeVinylPloce', requestOptions);
}
getMostWanted(){
    const requestOptions ={
        method: 'GET',
        headers:{'Content-Type': 'application/json'}
    };
    return fetch('/vinylPloca/najtrazenijeVinylPloce', requestOptions);
}
getBestGraded(){
    const requestOptions ={
        method: 'GET',
        headers:{'Content-Type': 'application/json'}
    };
    return fetch('/vinylPloca/najboljeOcjenjeneVinylPloce', requestOptions);
}
getZanrs(){
    const requestOptions ={
        method: 'GET'
    };
    return fetch('/zanr/list', requestOptions);
}

}

export default new plocaService();

