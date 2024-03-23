import request from '@/utils/request'

// 查询物种数据库信息列表
export function listSpecies(query) {
  return request({
    url: '/oss/species/list',
    method: 'get',
    params: query
  })
}

// 查询物种数据库信息详细
export function getSpecies(id) {
  return request({
    url: '/oss/species/' + id,
    method: 'get'
  })
}

// 新增物种数据库信息
export function addSpecies(data) {
  return request({
    url: '/oss/species',
    method: 'post',
    data: data
  })
}

// 修改物种数据库信息
export function updateSpecies(data) {
  return request({
    url: '/oss/species',
    method: 'put',
    data: data
  })
}

// 删除物种数据库信息
export function delSpecies(id) {
  return request({
    url: '/oss/species/' + id,
    method: 'delete'
  })
}
