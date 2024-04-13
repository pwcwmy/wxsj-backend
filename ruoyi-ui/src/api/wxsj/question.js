import request from '@/utils/request'

// 查询问卷答案组合列表
export function listQuestion(query) {
  return request({
    url: '/wxsj/question/list',
    method: 'get',
    params: query
  })
}

// 查询问卷答案组合详细
export function getQuestion(id) {
  return request({
    url: '/wxsj/question/' + id,
    method: 'get'
  })
}

// 新增问卷答案组合
export function addQuestion(data) {
  return request({
    url: '/wxsj/question',
    method: 'post',
    data: data
  })
}

// 修改问卷答案组合
export function updateQuestion(data) {
  return request({
    url: '/wxsj/question',
    method: 'put',
    data: data
  })
}

// 删除问卷答案组合
export function delQuestion(id) {
  return request({
    url: '/wxsj/question/' + id,
    method: 'delete'
  })
}
