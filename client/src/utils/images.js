function svg(label, emoji, bg1, bg2) {
  const svgContent = `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 480">
    <defs><linearGradient id="g" x1="0" y1="0" x2="1" y2="1">
      <stop offset="0%" stop-color="${bg1}"/><stop offset="100%" stop-color="${bg2}"/>
    </linearGradient></defs>
    <rect width="640" height="480" fill="url(#g)"/>
    <text x="320" y="210" font-size="96" text-anchor="middle">${emoji}</text>
    <text x="320" y="290" font-size="30" text-anchor="middle" fill="#363636" font-family="Arial,sans-serif">${label}</text>
  </svg>`
  return `data:image/svg+xml,${encodeURIComponent(svgContent)}`
}

export const PRODUCT_IMAGES = {
  guitar: svg('גיטרה', '🎸', '#fff4eb', '#ffd6a5'),
  piano: svg('פסנתר', '🎹', '#f3e8ff', '#ddd6fe'),
  microphone: svg('מיקרופון', '🎤', '#fce7f3', '#fbcfe8'),
  speaker: svg('רמקול', '🔊', '#e0e7ff', '#c7d2fe'),
  sofa: svg('ספה', '🛋️', '#fff7ed', '#fed7aa'),
  dining: svg('שולחן אוכל', '🪑', '#fef3c7', '#fde68a'),
  wardrobe: svg('ארון', '🚪', '#f5f5f4', '#d6d3d1'),
  bed: svg('מיטה', '🛏️', '#eff6ff', '#bfdbfe'),
  smartphone: svg('סמארטפון', '📱', '#ecfeff', '#a5f3fc'),
  macbook: svg('מחשב נייד', '💻', '#f0fdf4', '#bbf7d0'),
  fridge: svg('מקרר', '🧊', '#f0f9ff', '#bae6fd'),
  ps5: svg('PlayStation', '🎮', '#faf5ff', '#e9d5ff'),
  bag: svg('תיק', '👜', '#fdf2f8', '#f9a8d4'),
  watch: svg('שעון', '⌚', '#f8fafc', '#cbd5e1'),
  ebike: svg('אופניים', '🚲', '#ecfdf5', '#6ee7b7'),
  coffee: svg('מכונת קפה', '☕', '#fffbeb', '#fcd34d'),
  cookware: svg('סירים', '🍳', '#fef2f2', '#fca5a5'),
  electronics: svg('אלקטרוניקה', '🔌', '#eef2ff', '#a5b4fc'),
  misc: svg('שונות', '📦', '#f1f5f9', '#94a3b8'),
}

const CATEGORY_IMAGES = {
  כלי_נגינה: PRODUCT_IMAGES.guitar,
  רהיטים: PRODUCT_IMAGES.sofa,
  אלקטרוניקה: PRODUCT_IMAGES.electronics,
  בלי_חיים: PRODUCT_IMAGES.bag,
  שונות: PRODUCT_IMAGES.misc,
}

const NAME_TO_IMAGE = [
  ['גיטר', PRODUCT_IMAGES.guitar],
  ['guitar', PRODUCT_IMAGES.guitar],
  ['פסנתר', PRODUCT_IMAGES.piano],
  ['piano', PRODUCT_IMAGES.piano],
  ['מיקרופון', PRODUCT_IMAGES.microphone],
  ['מיקר', PRODUCT_IMAGES.microphone],
  ['microphone', PRODUCT_IMAGES.microphone],
  ['רמקול', PRODUCT_IMAGES.speaker],
  ['speaker', PRODUCT_IMAGES.speaker],
  ['ספה', PRODUCT_IMAGES.sofa],
  ['sofa', PRODUCT_IMAGES.sofa],
  ['שולחן אוכל', PRODUCT_IMAGES.dining],
  ['שולחן', PRODUCT_IMAGES.dining],
  ['table', PRODUCT_IMAGES.dining],
  ['כיסא', PRODUCT_IMAGES.dining],
  ['ארון', PRODUCT_IMAGES.wardrobe],
  ['wardrobe', PRODUCT_IMAGES.wardrobe],
  ['מיטה', PRODUCT_IMAGES.bed],
  ['bed', PRODUCT_IMAGES.bed],
  ['סמארטפון', PRODUCT_IMAGES.smartphone],
  ['galaxy', PRODUCT_IMAGES.smartphone],
  ['samsung', PRODUCT_IMAGES.smartphone],
  ['טלפון', PRODUCT_IMAGES.smartphone],
  ['macbook', PRODUCT_IMAGES.macbook],
  ['מקבוק', PRODUCT_IMAGES.macbook],
  ['מקרר', PRODUCT_IMAGES.fridge],
  ['fridge', PRODUCT_IMAGES.fridge],
  ['playstation', PRODUCT_IMAGES.ps5],
  ['ps5', PRODUCT_IMAGES.ps5],
  ['תיק', PRODUCT_IMAGES.bag],
  ['שעון', PRODUCT_IMAGES.watch],
  ['garmin', PRODUCT_IMAGES.watch],
  ['watch', PRODUCT_IMAGES.watch],
  ['אופניים', PRODUCT_IMAGES.ebike],
  ['אופנ', PRODUCT_IMAGES.ebike],
  ['ebike', PRODUCT_IMAGES.ebike],
  ['קפה', PRODUCT_IMAGES.coffee],
  ['coffee', PRODUCT_IMAGES.coffee],
  ['סירים', PRODUCT_IMAGES.cookware],
  ['סיר', PRODUCT_IMAGES.cookware],
  ['cookware', PRODUCT_IMAGES.cookware],
]

export function getProductImage(product) {
  const name = (product?.nameProduct || '').toLowerCase()

  for (const [keyword, url] of NAME_TO_IMAGE) {
    if (name.includes(keyword.toLowerCase())) return url
  }

  const cat = product?.categor?.categoryName
  return CATEGORY_IMAGES[cat] || PRODUCT_IMAGES.misc
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
  { key: 'רהיטים', label: 'סלון ואירוח', image: PRODUCT_IMAGES.sofa },
  { key: 'רהיטים', label: 'ארונות ואחסון', image: PRODUCT_IMAGES.wardrobe },
  { key: 'רהיטים', label: 'שולחנות וכיסאות', image: PRODUCT_IMAGES.dining },
  { key: 'רהיטים', label: 'חדר שינה', image: PRODUCT_IMAGES.bed },
  { key: 'כלי_נגינה', label: 'כלי נגינה', image: PRODUCT_IMAGES.guitar },
  { key: 'אלקטרוניקה', label: 'אלקטרוניקה', image: PRODUCT_IMAGES.electronics },
  { key: 'בלי_חיים', label: 'אביזרים', image: PRODUCT_IMAGES.bag },
  { key: 'שונות', label: 'שונות', image: PRODUCT_IMAGES.misc },
]
