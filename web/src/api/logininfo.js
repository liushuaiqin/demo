import request from "@/utils/request";
// 登录
export function login(data) {
    return request.get('/login', { params: data })
}

// 新增用户
export function userAddService(userModel) {
    return request.post("/login/add", userModel)
}