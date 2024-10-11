import request from "@/utils/request";

export function loginServeice(userId, password) {
    return request({
        url: "/admin/login",
        headers: {
            isToken: false,
        },
        method: "post",
        data: { userId, password },
    });
}