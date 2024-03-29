package com.project.bookstore.controller;

import com.project.bookstore.model.User;
import com.project.bookstore.model.dto.shoppingcrt.AddToCartRequestDto;
import com.project.bookstore.model.dto.shoppingcrt.CartItemsUpdateDto;
import com.project.bookstore.model.dto.shoppingcrt.ShoppingCartResponseDto;
import com.project.bookstore.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management", description = "Endpoints for managing shopping cart")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Add an item to Shopping cart",
            description = "Add an item to user's shopping cart")
    public ShoppingCartResponseDto addToCart(
            @RequestBody @Valid AddToCartRequestDto requestDto,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addToCart(requestDto, user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    @Operation(summary = "Get a shopping cart with items",
            description = "Get a shopping cart with all the present items")
    public ShoppingCartResponseDto getCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.getCartByUsedId(user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/cart-items/{cartItemId}")
    @Operation(summary = "Delete a cart item from a shopping cart",
            description = "Soft delete of a cart item from a shopping cart")
    public void deleteItem(@PathVariable Long cartItemId) {
        shoppingCartService.deleteItem(cartItemId);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/cart-items/{cartItemId}")
    @Operation(summary = "Update a quantity of books in a shopping cart",
            description = "Changes the number of books in a shopping cart")
    public ShoppingCartResponseDto updateCartItem(
            @PathVariable Long cartItemId,
            @RequestBody @Valid CartItemsUpdateDto updateDto,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.updateCartItem(cartItemId, updateDto, user.getId());
    }
}
