# Estándar de Codificación para Kotlin

## 1. Nombres de Variables y Constantes

- Todos los nombres deben estar en inglés.
- Usar `camelCase` para variables.
- Usar `SCREAMING_SNAKE_CASE` para constantes.

```kotlin
var customerName = "AliceSmith"
var retryLimit = 3
var hasSessionActive = false

const val MAX_RETRY_COUNT = 5
const val SERVICE_ENDPOINT = "https://api.mysite.com"
```

## 2. Nombres de Funciones

- Usar `camelCase`.
- El nombre debe describir claramente su propósito.
- Si la función realiza una acción, el nombre debe iniciar con un verbo.

```kotlin
fun loadUserDetails(): UserProfile { ... }
fun updateAccountInfo() { ... }
fun computeFinalAmount(subtotal: Double, discount: Double): Double { ... }
```

## 3. Nombres de Clases

- Usar `PascalCase`.
- Evitar abreviaciones y utilizar nombres descriptivos.

```kotlin
class UserProfile { ... }
class PaymentViewModel { ... }
class CartSummaryView { ... }
```

## 4. Naming Conventions para Arquitectura MVVM

- **Vistas** terminan con `View`.
- **Modelos** terminan con `Model`.
- **ViewModels** terminan con `ViewModel`.

```kotlin
class SignupView { ... }
class ProductModel { ... }
class OrderViewModel { ... }
```

## 5. Uso de Tipos de Datos

- Evitar tipos primitivos innecesarios y utilizar los adecuados.
- Preferir `val` sobre `var` si el valor no cambia.

```kotlin
val customerEmail: String = "alice@example.com"
var accountBalance: Double = 120.50
val isAdminUser: Boolean = false
```

## 6. Formato y Estilo de Código

- Usar el formateador de código de Android Studio para mantener un código limpio.
- Atajo para formatear código:
  - **Windows/Linux**: `Ctrl + Alt + L`
  - **macOS**: `Cmd + Option + L`

```kotlin
if (hasSessionActive) {
    displayDashboard()
} else {
    promptUserLogin()
}
```

## 7. Comentarios y Documentación

- Usar `//` para comentarios cortos.
- Usar `/** ... */` para documentar funciones y clases.

```kotlin
/**
 * Computes the final cost after applying a discount.
 *
 * @param subtotal Initial cost before discount
 * @param discount Discount percentage
 * @return Final price after applying the discount
 */
fun computeFinalAmount(subtotal: Double, discount: Double): Double {
    return subtotal * (1 - discount / 100)
}
```

## 8. Organización del Código

- Declarar variables antes de las funciones.
- Agrupar funciones relacionadas.

## 9. Nombres para Assets de Imágenes

- Usar nombres en inglés.
- Solo minúsculas y guiones bajos (`_`).
- No usar espacios, caracteres especiales ni mayúsculas.

### ✅ Ejemplo de nombres correctos:

```
ic_user_avatar.png
bg_welcome_screen.jpg
btn_submit_pressed.png
banner_promotion.png
ic_settings_gear.xml
```

### ❌ Ejemplo de nombres incorrectos:

```
ProfileIcon.png   (No usar mayúsculas)
WELCOME_BG.JPG    (No usar guiones medios)
login button.png  (No usar espacios)
UserSettings.xml  (No usar PascalCase)
```

### 📌 Convenciones recomendadas según tipo de imagen:

| Prefijo | Uso     | Ejemplo                 |
|---------|--------|-------------------------|
| `ic_`   | Íconos | `ic_dashboard.png`, `ic_search.xml` |
| `bg_`   | Fondos | `bg_home_screen.jpg` |
| `btn_`  | Botones | `btn_register_pressed.png` |
| `banner_` | Banners | `banner_sale.png` |

## ✅ Resumen del Estándar de Codificación

✔ Seguir las convenciones de nomenclatura para variables, clases y assets.
✔ Usar el formateador de código de Android Studio.
✔ Documentar funciones con comentarios.
✔ Organizar el código correctamente.

