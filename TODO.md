# Piingo Chat Service — Features Todo

## Conversations
- [ ] Get a conversation by ID
- [ ] Get all conversations for a user (inbox)
- [ ] Delete a conversation
- [ ] Validate that participant user IDs actually exist before creating a conversation (requires HTTP call to user-service — inter-service communication)

## Messaging
- [ ] Send a message in a conversation
- [ ] Get all messages in a conversation (with pagination)
- [ ] Mark a message as read
- [ ] Get unread message count for a user (per conversation or total)

## Participants
- [ ] Get all participants in a conversation
- [ ] Add a participant to a conversation (group chat)
- [ ] Remove a participant from a conversation

## Real-time
- [ ] WebSocket support — push new messages to connected clients in real time
- [ ] Typing indicator — notify other participants when someone is typing

## Message Replies
- [ ] Add `replyToMessageId` self-referencing field on `Message` entity
- [ ] Map `@ManyToOne` from `Message` → parent `Message` and `@OneToMany` for replies
- [ ] API to send a reply to a specific message
- [ ] API to get replies for a message

## Conversation Inbox Preview (Last Message)
- [ ] Map `@OneToOne` from `Conversation` → `Message` (most recent message)
- [ ] Keep this updated every time a new message is sent
- [ ] Include last message preview in the get conversations for a user (inbox) response

## Search
- [ ] Search messages within a conversation by keyword

## Notifications
- [ ] Unread badge count per conversation
- [ ] Push notification when a new message arrives (for offline users)
