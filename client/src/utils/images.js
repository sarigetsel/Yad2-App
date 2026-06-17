export const PRODUCT_IMAGES = {
  guitar: 'https://images.unsplash.com/photo-1516925856310-b593457a71a5?w=600&h=450&fit=crop&q=80',
  piano: 'https://images.unsplash.com/photo-1520523839897-bd0b52f945a0?w=600&h=450&fit=crop&q=80',
  microphone: 'https://images.unsplash.com/photo-1598488035139-bdbb2231ce04?w=600&h=450&fit=crop&q=80',
  sofa: 'https://images.unsplash.com/photo-1555041469-a586c0361544?w=600&h=450&fit=crop&q=80',
  dining: 'https://images.unsplash.com/photo-1617806118773-1e0e938d9a24?w=600&h=450&fit=crop&q=80',
  wardrobe: 'https://images.unsplash.com/photo-1616598336755-72e7888753f8?w=600&h=450&fit=crop&q=80',
  bed: 'https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?w=600&h=450&fit=crop&q=80',
  smartphone: 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=600&h=450&fit=crop&q=80',
  macbook: 'https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=600&h=450&fit=crop&q=80',
  fridge: 'https://images.unsplash.com/photo-1571175443880-49a1e1e2d8a2?w=600&h=450&fit=crop&q=80',
  ps5: 'https://images.unsplash.com/photo-1606813907291-d86efa9b94e4?w=600&h=450&fit=crop&q=80',
  bag: 'https://images.unsplash.com/photo-1548036328-c9fa89d128fa?w=600&h=450&fit=crop&q=80',
  watch: 'https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=600&h=450&fit=crop&q=80',
  ebike: 'https://images.unsplash.com/photo-1571068316344-75bc76f77890?w=600&h=450&fit=crop&q=80',
  coffee: 'https://images.unsplash.com/photo-1517668808822-9ebb02f2a0e6?w=600&h=450&fit=crop&q=80',
  cookware: 'https://images.unsplash.com/photo-1584990347499-66a5b6c9112a?w=600&h=450&fit=crop&q=80',
  tv: 'https://images.unsplash.com/photo-1593359677873-a4bb59244616?w=600&h=450&fit=crop&q=80',
  desk: 'https://images.unsplash.com/photo-1518455027359-f3f8164ba6bd?w=600&h=450&fit=crop&q=80',
}

const CATEGORY_IMAGES = {
  כלי_נגינה: PRODUCT_IMAGES.guitar,
  רהיטים: PRODUCT_IMAGES.sofa,
  אלקטרוניקה: PRODUCT_IMAGES.smartphone,
  בלי_חיים: PRODUCT_IMAGES.bag,
  שונות: PRODUCT_IMAGES.coffee,
}

const NAME_TO_IMAGE = [
  ['גיטר', PRODUCT_IMAGES.guitar],
  ['guitar', PRODUCT_IMAGES.guitar],
  ['פסנתר', PRODUCT_IMAGES.piano],
  ['piano', PRODUCT_IMAGES.piano],
  ['מיקר', PRODUCT_IMAGES.microphone],
  ['mic', PRODUCT_IMAGES.microphone],
  ['ספה', PRODUCT_IMAGES.sofa],
  ['sofa', PRODUCT_IMAGES.sofa],
  ['שולחן', PRODUCT_IMAGES.dining],
  ['table', PRODUCT_IMAGES.dining],
  ['ארון', PRODUCT_IMAGES.wardrobe],
  ['מיטה', PRODUCT_IMAGES.bed],
  ['bed', PRODUCT_IMAGES.bed],
  ['סמארטפון', PRODUCT_IMAGES.smartphone],
  ['galaxy', PRODUCT_IMAGES.smartphone],
  ['טלפון', PRODUCT_IMAGES.smartphone],
  ['macbook', PRODUCT_IMAGES.macbook],
  ['מקבוק', PRODUCT_IMAGES.macbook],
  ['מקרר', PRODUCT_IMAGES.fridge],
  ['fridge', PRODUCT_IMAGES.fridge],
  ['playstation', PRODUCT_IMAGES.ps5],
  ['ps5', PRODUCT_IMAGES.ps5],
  ['תיק', PRODUCT_IMAGES.bag],
  ['bag', PRODUCT_IMAGES.bag],
  ['שעון', PRODUCT_IMAGES.watch],
  ['watch', PRODUCT_IMAGES.watch],
  ['אופנ', PRODUCT_IMAGES.ebike],
  ['bike', PRODUCT_IMAGES.ebike],
  ['קפה', PRODUCT_IMAGES.coffee],
  ['coffee', PRODUCT_IMAGES.coffee],
  ['סיר', PRODUCT_IMAGES.cookware],
  ['cook', PRODUCT_IMAGES.cookware],
  ['טלוויז', PRODUCT_IMAGES.tv],
  ['שולחן עבודה', PRODUCT_IMAGES.desk],
]

export function getProductImage(product) {
  if (product?.picture?.startsWith('http')) return product.picture
  if (product?.picture) {
    return `/api/images/${encodeURIComponent(product.picture)}`
  }
  const name = (product?.nameProduct || '').toLowerCase()
  for (const [keyword, url] of NAME_TO_IMAGE) {
    if (name.includes(keyword.toLowerCase())) return url
  }
  const cat = product?.categor?.categoryName
  return CATEGORY_IMAGES[cat] || PRODUCT_IMAGES.sofa
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

export const CATEGORY_NAV = [
  {
    key: 'רהיטים',
    label: 'סלון ואירוח',
    image: 'https://images.unsplash.com/photo-1555041469-a586c0361544?w=200&h=200&fit=crop&q=80',
  },
  {
    key: 'רהיטים',
    label: 'ארונות ואחסון',
    image: 'https://images.unsplash.com/photo-1616598336755-72e7888753f8?w=200&h=200&fit=crop&q=80',
  },
  {
    key: 'רהיטים',
    label: 'שולחנות וכיסאות',
    image: 'https://images.unsplash.com/photo-1617806118773-1e0e938d9a24?w=200&h=200&fit=crop&q=80',
  },
  {
    key: 'רהיטים',
    label: 'חדר שינה',
    image: 'https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?w=200&h=200&fit=crop&q=80',
  },
  {
    key: 'כלי_נגינה',
    label: 'כלי נגינה',
    image: 'https://images.unsplash.com/photo-1516925856310-b593457a71a5?w=200&h=200&fit=crop&q=80',
  },
  {
    key: 'אלקטרוניקה',
    label: 'אלקטרוניקה',
    image: 'https://images.unsplash.com/photo-1498049794561-7780e7231661?w=200&h=200&fit=crop&q=80',
  },
  {
    key: 'בלי_חיים',
    label: 'אביזרים',
    image: 'https://images.unsplash.com/photo-1548036328-c9fa89d128fa?w=200&h=200&fit=crop&q=80',
  },
  {
    key: 'שונות',
    label: 'שונות',
    image: 'https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=200&h=200&fit=crop&q=80',
  },
]
