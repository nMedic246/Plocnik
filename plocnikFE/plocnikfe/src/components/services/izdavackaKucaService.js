import { authHeader } from '../helpers/authHeader';
class izdavackaKucaService{

getIzdavackeKuce(){
    const requestOptions ={
        method: 'GET',
        headers:{'Content-Type': 'application/json'}
    };
    return fetch('/izdavackaKuca/list', requestOptions);
}  

getIzdavackaKucaById(id){
        const requestOptions ={
            method: 'GET',
            headers:{'Content-Type': 'application/json'}
        };
        return fetch('/izdavackaKuca/'+id, requestOptions);
}

getIzdavackaKucaImg(id){
    const requserOptions = {
        method:'GET'
    }
    return fetch('/izdavackaKuca/slika/'+id,requserOptions);
 }

 getIzdavackaKucaPloce(id){
    const requestOptions ={
        method: 'GET',
        headers:{'Content-Type': 'application/json'}
    };
    return fetch('/izdavackaKuca/popisPloca/'+id, requestOptions);
 }

 getMjesta(){
    const requestOptions ={
        method: 'GET',
        headers:{'Content-Type': 'application/json'}
    };
    return fetch('/mjesto/list', requestOptions);
 }
 getVlasnici(){
    const requestOptions ={
        method: 'GET',
        headers:{'Content-Type': 'application/json'}
    };
    return fetch('/vlasnik/list', requestOptions);
 }
 dodajIzdavackuKucu(nazivIzdavackaKuca,infoIzdavackaKuca,idMjesto,idVlasnik){
    const requestOptions = {
        method: 'POST',
        headers: authHeader() ,
        body: JSON.stringify({nazivIzdavackaKuca,infoIzdavackaKuca,idMjesto,idVlasnik })
    };

    return fetch('/izdavackaKuca/dodajIzdavackuKucu', requestOptions);
 }
 obrisiKucu(id){
    const requestOptions={
        method:'DELETE',
        headers:authHeader()
    };
    return fetch('/izdavackaKuca/'+id+'/obrisiIzdavackuKucu',requestOptions);
}

searchKuce(filter){
    const requestOptions={
        method:'GET',
    }
    if(filter.trim()==''){
        return  fetch('izdavackaKuca/list',requestOptions);
    }else{
        return fetch('izdavackaKuca/filter/'+filter.trim(),requestOptions);
    }
    
}
}

export default new izdavackaKucaService();
