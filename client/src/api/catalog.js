import api from './axios'

export const categoriesApi = {
  getAll: () => api.get('/category/getAll'),
}

export const areasApi = {
  getAll: () => api.get('/area/getAll'),
}
