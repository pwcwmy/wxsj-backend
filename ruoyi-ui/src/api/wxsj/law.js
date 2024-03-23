import request from '@/utils/request'

// 查询法律知识图谱列表
export function listLaw(query) {
  return request({
    url: '/wxsj/law/list',
    method: 'get',
    params: query
  })
}

// 查询法律知识图谱详细
export function getLaw(id) {
  return request({
    url: '/wxsj/law/' + id,
    method: 'get'
  })
}

// 新增法律知识图谱
export function addLaw(data) {
  return request({
    url: '/wxsj/law',
    method: 'post',
    data: data
  })
}

// 修改法律知识图谱
export function updateLaw(data) {
  return request({
    url: '/wxsj/law',
    method: 'put',
    data: data
  })
}

// 删除法律知识图谱
export function delLaw(id) {
  return request({
    url: '/wxsj/law/' + id,
    method: 'delete'
  })
}
