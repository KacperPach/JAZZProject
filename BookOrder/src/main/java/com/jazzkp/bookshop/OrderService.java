package com.jazzkp.bookshop;

import com.jazzkp.bookshop.dto.BookOrder;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import dto.BookOrderRequest;

import exception.NoBookIDException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    ResponseEntity<List<BookOrder>> receiveOrder (List<BookOrderRequest> orderRequests) {
        orderRequests.forEach( b -> {
            if (b.getNumOfVisits() > 9){
                if(b.getBookId() == null)
                    throw new NoBookIDException();
                BookOrder bo = orderRepository.findById(b.getBookId()).orElse(new BookOrder());
                bo.setBookId(b.getBookId());
                bo.setOrderAmount((int)b.getNumOfVisits()/10);
                orderRepository.save(bo);
            }
        });
        return ResponseEntity.ok(orderRepository.findAll());
    }

    public void exportOrder(HttpServletResponse response) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, response.getOutputStream());


            document.open();
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTitle.setSize(18);
            Paragraph paragraph = new Paragraph("Order", fontTitle);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);

            Font fontBody = FontFactory.getFont(FontFactory.HELVETICA);
            fontBody.setSize(12);

            orderRepository.findAll().forEach(o -> {
                Paragraph orderParagraph = new Paragraph(
                        String.format("Book of ID: %s, in amount of %s", o.getBookId(), o.getOrderAmount()),
                        fontBody);
                orderParagraph.setAlignment(Paragraph.ALIGN_LEFT);
                document.add(orderParagraph);
            });

            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
