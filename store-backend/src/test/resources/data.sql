insert into products(id, version, name, category, description, price) values
(hibernate_sequence.nextval, 1, 'Kayak', 'Watersports', 'A boat for one person', 275),
(hibernate_sequence.nextval, 1, 'Lifejacket', 'Watersports', 'Protective and fashionable', 48.95),
(hibernate_sequence.nextval, 1, 'Soccer Ball', 'Soccer', 'FIFA-approved size and weight', 19.50),
(hibernate_sequence.nextval, 1, 'Corner Flags', 'Soccer', 'Give your playing field a professional touch', 34.95),
(hibernate_sequence.nextval, 1, 'Stadium', 'Soccer', 'Flat-packed 35,000-seat stadium', 79500),
(hibernate_sequence.nextval, 1, 'Thinking Cap', 'Chess', 'Improve brain efficiency by 75%', 1),
(hibernate_sequence.nextval, 1, 'Unsteady Chair', 'Chess', 'Secretly give your opponent a disadvantage', 29.95),
(hibernate_sequence.nextval, 1, 'Human Chess Board', 'Chess', 'A fun game for the family', 75),
(hibernate_sequence.nextval, 1, 'Bling Bling King', 'Chess', 'Gold-plated, diamond-studded King', 1200);

insert into cart(id, version, item_count, cart_price) values
(hibernate_sequence.nextval, 0, 1, 3);

insert into cartline(id, version, quantity, product_id, cart_id) values
(hibernate_sequence.nextval, 0, 3, 6, 10);

insert into orders(id, version, name, address, city, state, zip, country, shipped, cart_id) values
(hibernate_sequence.nextval, 0, 'TestOrder', 'Magic st.', 'Kazan', 'Tatarstan', '12345', 'Russia', true, 10);
