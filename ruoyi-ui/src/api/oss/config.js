import request from '@/utils/request'

// 查询对象存储配置列表
export function listConfig(query) {
  return request({
    url: '/oss/config/list',
    method: 'get',
    params: query
  })
}

// 查询对象存储配置详细
export function getConfig(configName) {
  return request({
    url: '/oss/config/' + configName,
    method: 'get'
  })
}

// 新增对象存储配置
export function addConfig(data) {
  return request({
    url: '/oss/config',
    method: 'post',
    data: data
  })
}

// 修改对象存储配置
export function updateConfig(data) {
  return request({
    url: '/oss/config',
    method: 'put',
    data: data
  })
}

// 删除对象存储配置
export function delConfig(configName) {
  return request({
    url: '/oss/config/' + configName,
    method: 'delete'
  })
}
