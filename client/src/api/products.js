import api from './axios'

export const productsApi = {
  getAll: () => api.get('/product/getAll'),
  getAllDto: () => api.get('/product/getAlldTO'),
  getById: (id) => api.get(`/product/${id}`),
  getByIdDto: (id) => api.get(`/product/${id}/dto`),
  addWithImage: (formData) =>
    api.post('/product/addDto', formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    }),
}
