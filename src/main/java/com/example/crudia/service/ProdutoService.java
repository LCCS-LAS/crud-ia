package com.example.crudia.service;

import com.example.crudia.dto.ProdutoRequest;
import com.example.crudia.dto.ProdutoResponse;
import com.example.crudia.entity.Produto;
import com.example.crudia.exception.ResourceNotFoundException;
import com.example.crudia.repository.ProdutoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoResponse criar(ProdutoRequest request) {
        Produto produto = mapToEntity(request);
        Produto salvo = produtoRepository.save(produto);
        return mapToResponse(salvo);
    }

    public List<ProdutoResponse> listarTodos() {
        return produtoRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public ProdutoResponse buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não foi encontrado"));
        return mapToResponse(produto);
    }

    public ProdutoResponse atualizar(Long id, ProdutoRequest request) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não foi encontrado"));

        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPreco(request.getPreco());
        produto.setQuantidadeEstoque(request.getQuantidadeEstoque());

        Produto atualizado = produtoRepository.save(produto);
        return mapToResponse(atualizado);
    }

    public void deletar(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não foi encontrado"));
        produtoRepository.delete(produto);
    }

    private Produto mapToEntity(ProdutoRequest request) {
        Produto produto = new Produto();
        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPreco(request.getPreco());
        produto.setQuantidadeEstoque(request.getQuantidadeEstoque());
        return produto;
    }

    private ProdutoResponse mapToResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQuantidadeEstoque());
    }
}
