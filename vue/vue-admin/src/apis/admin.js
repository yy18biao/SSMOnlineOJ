import request from "@/utils/request";

export function loginServeice(userId, password) {
    return request({
        url: "/user/login",
        headers: {
            isToken: false,
        },
        method: "post",
        data: { userId, password },
    });
}

export function getAdminService() {
    return request({
        url: "/user/getUser",
        method: "get",
    });
}

export function logoutService() {
    return request({
        url: "/user/logout",
        method: "delete",
    });
}