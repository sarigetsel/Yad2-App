const CATEGORY_IMAGES = {
  כלי_נגינה: 'https://images.unsplash.com/photo-1516925856310-b593457a71a5?w=600&q=80',
  רהיטים: 'https://images.unsplash.com/photo-1555041469-a586c0361544?w=600&q=80',
  אלקטרוניקה: 'https://images.unsplash.com/photo-1498049794561-7780e7231661?w=600&q=80',
  בלי_חיים: 'https://images.unsplash.com/photo-1548036328-c9fa89d128fa?w=600&q=80',
  שונות: 'https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=600&q=80',
}

const PRODUCT_KEYWORDS = {
  guitar: 'https://images.unsplash.com/photo-1516925856310-b593457a71a5?w=600&q=80',
  piano: 'https://images.unsplash.com/photo-1520523839897-bd0b52f945a0?w=600&q=80',
  microphone: 'https://images.unsplash.com/photo-1598488035139-bdbb2231ce04?w=600&q=80',
  sofa: 'https://images.unsplash.com/photo-1555041469-a586c0361544?w=600&q=80',
  dining: 'https://images.unsplash.com/photo-1617806118773-1e0e938d9a24?w=600&q=80',
  wardrobe: 'https://images.unsplash.com/photo-1616598336755-72e7888753f8?w=600&q=80',
  iphone: 'https://images.unsplash.com/photo-1678652197831-2a6408a3d3c8?w=600&q=80',
  macbook: 'https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=600&q=80',
  fridge: 'https://images.unsplash.com/photo-1571175443880-49a1e1e2d8a2?w=600&q=80',
  ps5: 'https://images.unsplash.com/photo-1606813907291-d86efa9b94e4?w=600&q=80',
  bag: 'https://images.unsplash.com/photo-1548036328-c9fa89d128fa?w=600&q=80',
  watch: 'https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=600&q=80',
  ebike: 'https://images.unsplash.com/photo-1571068316344-75bc76f77890?w=600&q=80',
  coffee: 'https://images.unsplash.com/photo-1517668808822-9ebb02f2a0e6?w=600&q=80',
  cookware: 'https://images.unsplash.com/photo-1584990347499-66a5b6c9112a?w=600&q=80',
}

export function getProductImage(product) {
  if (product?.picture) {
    return `/api/images/${encodeURIComponent(product.picture)}`
  }
  const name = (product?.nameProduct || '').toLowerCase()
  if (name.includes('גיטר') || name.includes('guitar')) return PRODUCT_KEYWORDS.guitar
  if (name.includes('פסנתר') || name.includes('piano')) return PRODUCT_KEYWORDS.piano
  if (name.includes('מיקר') || name.includes('mic')) return PRODUCT_KEYWORDS.microphone
  if (name.includes('ספה') || name.includes('sofa')) return PRODUCT_KEYWORDS.sofa
  if (name.includes('שולחן') || name.includes('table')) return PRODUCT_KEYWORDS.dining
  if (name.includes('ארון')) return PRODUCT_KEYWORDS.wardrobe
  if (name.includes('iphone') || name.includes('אייפון')) return PRODUCT_KEYWORDS.iphone
  if (name.includes('macbook') || name.includes('מקבוק')) return PRODUCT_KEYWORDS.macbook
  if (name.includes('מקרר') || name.includes('fridge')) return PRODUCT_KEYWORDS.fridge
  if (name.includes('playstation') || name.includes('ps5')) return PRODUCT_KEYWORDS.ps5
  if (name.includes('תיק') || name.includes('bag')) return PRODUCT_KEYWORDS.bag
  if (name.includes('שעון') || name.includes('watch')) return PRODUCT_KEYWORDS.watch
  if (name.includes('אופנ') || name.includes('bike')) return PRODUCT_KEYWORDS.ebike
  if (name.includes('קפה') || name.includes('coffee')) return PRODUCT_KEYWORDS.coffee
  if (name.includes('סיר') || name.includes('cook')) return PRODUCT_KEYWORDS.cookware

  const cat = product?.categor?.categoryName
  return CATEGORY_IMAGES[cat] || CATEGORY_IMAGES.שונות
}

export function formatCategory(name) {
  return (name || '').replace(/_/g, ' ')
}

export function formatPrice(price) {
  return new Intl.NumberFormat('he-IL', {
    style: 'currency',
    currency: 'ILS',
    maximumFractionDigits: 0,
  }).format(price)
}

export function formatStatus(status) {
  return (status || '').replace(/_/g, ' ')
}
