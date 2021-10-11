import { userConstants } from '../constants';
import { userService } from '../services/userService';
import { alertActions } from './alertActions';
import { history } from '../helpers/history';

export const userActions = {
    login,
    logout,
    register,
};

function login(korisnickoIme, zaporka) {
    return dispatch => {
        dispatch(request({ korisnickoIme }));

        userService.login(korisnickoIme, zaporka)
            .then(
                user => { 
                    dispatch(success(user));
                    history.push('');  
                },
                error => {
                    dispatch(failure(error.toString()));
                    dispatch(alertActions.error(error.toString()));
                }
            );
    };

    function request(user) { return { type: userConstants.LOGIN_REQUEST, user } }
    function success(user) { return { type: userConstants.LOGIN_SUCCESS, user } }
    function failure(error) { return { type: userConstants.LOGIN_FAILURE, error } }
}

function logout() {
    userService.logout();
    return { type: userConstants.LOGOUT };
}

function register(user) {
    console.log("tu sam u action");
    return dispatch => {
        dispatch(request(user));

        userService.register(user)
            .then(
                user => { 
                    dispatch(success());
                    history.push('/login');
                    dispatch(alertActions.success('Uspješna registracija'));
                },
                error => {
                    dispatch(failure(error.toString()));
                    dispatch(alertActions.error(error.toString()));
                }
            );
    };

    function request(user) { return { type: userConstants.REGISTER_REQUEST, user } }
    function success(user) { return { type: userConstants.REGISTER_SUCCESS, user } }
    function failure(error) { return { type: userConstants.REGISTER_FAILURE, error } }
}