import request from '@/utils/request'

// 查询登录用户信息列表
export function listAdmin(query) {
  return request({
    url: '/oss/admin/list',
    method: 'get',
    params: query
  })
}

// 查询登录用户信息详细
export function getAdmin(adminId) {
  return request({
    url: '/oss/admin/' + adminId,
    method: 'get'
  })
}

// 新增登录用户信息
export function addAdmin(data) {
  return request({
    url: '/oss/admin',
    method: 'post',
    data: data
  })
}

// 修改登录用户信息
export function updateAdmin(data) {
  return request({
    url: '/oss/admin',
    method: 'put',
    data: data
  })
}

// 删除登录用户信息
export function delAdmin(adminId) {
  return request({
    url: '/oss/admin/' + adminId,
    method: 'delete'
  })
}
