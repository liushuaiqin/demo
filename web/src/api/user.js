import request from "@/utils/request";

// 获取所有用户
export function userGetAllService() {
    return request.get("/user/getAll")
}

// 根据id删除用户
export const userDeleteService = (id) => {
    return request.delete('/user/deleteById/' + id)
}

// 根据id修改用户
export const userUpdateService = (userModel) => {
    return request.put('user/update', userModel)
}

// 条件分页查询用户
export function userPageService(params) {
    return request.get("/user/page", {params: params})
}

// 登录
export function userSearchService(params) {
    return request.get('/user/search', { params: params })
}